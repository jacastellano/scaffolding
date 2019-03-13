import { Component, OnInit, ViewChild } from '@angular/core';
import { IonInfiniteScroll } from '@ionic/angular';

import { Entity } from '../models/entity.model';
import { EntityService } from '../services/entity.service';

const INITIAL_LIST_SIZE: number = 12;
const INCREMENT: number = 5;

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page implements OnInit {

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

  addEntity() {
    console.log('navidate to /list/new');
  }

  editEntity(id: number) {
    console.log('navidate to /list/id');
  }

  ngOnInit() {
    
    this.completeEntityList = [];
    
    this.service.findAllEntities()
      
      .then(data => {
        console.log('status:' + data.status);
        const collection: Entity[] = JSON.parse(data.data);
        collection.forEach(e => {
          const entity: Entity = this.convertToEntity(e);
          this.completeEntityList.push(entity);
        });
        // load data
        this.loadMoreEntities(INITIAL_LIST_SIZE);
      })

      .catch(error => {
        console.log(error);
      });

  }

}
