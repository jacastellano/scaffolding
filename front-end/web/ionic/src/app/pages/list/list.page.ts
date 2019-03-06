import { Component, OnInit, ViewChild } from '@angular/core';
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
export class ListPage implements OnInit {

  @ViewChild(IonInfiniteScroll) infiniteScroll: IonInfiniteScroll;

  public completeEntityList: Entity[];
  public displayedEntityList: Entity[];
  public lastIndexDisplayed: number = INITIAL_LIST_SIZE;

  constructor(private service: EntityService) { }

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

  ngOnInit() {

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
          console.log(error);
        });

  }

}
