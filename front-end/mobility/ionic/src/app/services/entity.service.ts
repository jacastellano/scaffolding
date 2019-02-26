import { Injectable } from '@angular/core';

import { Entity } from '../models/entity.model';

const ENTITIES: Entity[] = [
  {
    entityId: 1,
    entityTitle: "Entity 1",
    entityDescription: "Entity 1 in service",
    createDate: new Date("01/01/2019").toISOString(),
    updateDate: new Date("02/01/2019").toISOString(),
  },
  {
    entityId: 2,
    entityTitle: "Entity 2",
    entityDescription: "Entity 2 in service",
    createDate: new Date("03/01/2019").toISOString(),
    updateDate: new Date("04/01/2019").toISOString(),
  },
  {
    entityId: 3,
    entityTitle: "Entity 3",
    entityDescription: "Entity 3 in service",
    createDate: new Date("05/01/2019").toISOString(),
    updateDate: new Date("06/01/2019").toISOString(),
  },
  {
    entityId: 4,
    entityTitle: "Entity 4",
    entityDescription: "Entity 4 in service",
    createDate: new Date("07/01/2019").toISOString(),
    updateDate: new Date("08/01/2019").toISOString(),
  },
  {
    entityId: 5,
    entityTitle: "Entity 5",
    entityDescription: "Entity 5 in service",
    createDate: new Date().toISOString(),
    updateDate: new Date().toISOString(),
  }
]

@Injectable({
  providedIn: 'root'
})
export class EntityService {

  constructor() { }

  findEntityById(id: number): Entity {
    throw new Error("Method not implemented.");
  }

  findAllEntities(): Entity[] {
    return ENTITIES;
  }

  createEntity(entity: Entity): number {
    throw new Error("Method not implemented.");
  }

  updateEntity(entity: Entity, id: number) {
    throw new Error("Method not implemented.");
  }

  deleteEntity(id: number) {
    throw new Error("Method not implemented.");
  }

}
