import { Component, OnInit } from '@angular/core';

import { Entity } from '../models/entity.model';
import { EntityService } from '../services/entity.service';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss']
})
export class Tab2Page implements OnInit {

  public entityList: Entity[];

  constructor(private service: EntityService) { }

  ngOnInit(): void {
    this.entityList = this.service.findAllEntities();
  }
}
