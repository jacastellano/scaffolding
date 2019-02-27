import { Component, OnInit } from '@angular/core';

import { Entity } from 'src/app/models/entity.model';
import { EntityService } from 'src/app/services/entity.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.page.html',
  styleUrls: ['./list.page.scss'],
})
export class ListPage implements OnInit {

  public entityList: Entity[];

  constructor(private service: EntityService) { }

  ngOnInit() {
    this.entityList = this.service.findAllEntities();
  }

}
