import { Component, ViewChild } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { IonInfiniteScroll } from '@ionic/angular';

import { Entity } from 'src/app/models/entity.model';
import { EntityService } from 'src/app/services/entity.service';

const INITIAL_LIST_SIZE: number = 15;
const INCREMENT: number = 5;

@Component({
  selector: 'app-list',
  templateUrl: './list.page.html',
  styleUrls: ['./list.page.scss'],
})
export class ListPage {

  @ViewChild(IonInfiniteScroll) infiniteScroll: IonInfiniteScroll;

  public completeEntityList: Entity[];
  public displayedEntityList: Entity[];
  public lastIndexDisplayed: number = INITIAL_LIST_SIZE;

  constructor(
    private service: EntityService,
    private router: Router) {

    // TODO Improve solution using ngOnInit
    // Problem: After Navigation End when you create or update an entity,
    // list is not actualized (ngOnInit is not invoked).
    router.events.subscribe((event) => {
      if ((event instanceof NavigationEnd)
        && (event.url === '/list')) {
        console.log(event.url);
        this.initialize();
      }
    });

  }

  loadData(event) {
    setTimeout(() => {
      event.target.complete();
      if (this.displayedEntityList.length >= this.completeEntityList.length) {
        event.target.disabled = true;
      } else {
        this.loadMoreEntities(INCREMENT);
      }
    }, 500);
  }

  loadMoreEntities(n: number) {
    const currentSize = (this.displayedEntityList) ? this.displayedEntityList.length : 0;
    this.displayedEntityList = this.completeEntityList.filter((value, index) => index < currentSize + n);
  }

  convertToEntity(data: any): Entity {

    const createDate = new Date(data.createDate);
    const updateDate = new Date(data.updateDate);

    const entity: Entity = {
      ...data,
      createDate: createDate.toISOString(),
      updateDate: updateDate.toISOString(),
    };

    return entity;
  }

  addEntity() {
    this.router.navigateByUrl(this.router.url + '/new');
  }

  editEntity(id: number) {
    this.router.navigateByUrl(this.router.url + '/' + id);
  }

  initialize(): void {

    console.log('ListPage:initialize()');

    this.completeEntityList = [];

    this.service.findAllEntities()
      .subscribe(

        (response) => {

          const data = response.body;
          data.forEach((element) => {
            const entity: Entity = this.convertToEntity(element);
            this.completeEntityList.push(entity);
          });
          // load data
          this.loadMoreEntities(INITIAL_LIST_SIZE);
        },

        (error) => {
          console.log('findAllEntities:ERROR:' + error);
        });

  }

}
