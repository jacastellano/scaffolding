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
  },
  {
    entityId: 6,
    entityTitle: "Entity 6",
    entityDescription: "Entity 6 in service",
    createDate: new Date("01/01/2019").toISOString(),
    updateDate: new Date("02/01/2019").toISOString(),
  },
  {
    entityId: 7,
    entityTitle: "Entity 7",
    entityDescription: "Entity 7 in service",
    createDate: new Date("03/01/2019").toISOString(),
    updateDate: new Date("04/01/2019").toISOString(),
  },
  {
    entityId: 8,
    entityTitle: "Entity 8",
    entityDescription: "Entity 8 in service",
    createDate: new Date("05/01/2019").toISOString(),
    updateDate: new Date("06/01/2019").toISOString(),
  },
  {
    entityId: 9,
    entityTitle: "Entity 9",
    entityDescription: "Entity 9 in service",
    createDate: new Date("07/01/2019").toISOString(),
    updateDate: new Date("08/01/2019").toISOString(),
  },
  {
    entityId: 10,
    entityTitle: "Entity 10",
    entityDescription: "Entity 10 in service",
    createDate: new Date("01/01/2019").toISOString(),
    updateDate: new Date("02/01/2019").toISOString(),
  },
  {
    entityId: 11,
    entityTitle: "Entity 11",
    entityDescription: "Entity 11 in service",
    createDate: new Date("01/01/2019").toISOString(),
    updateDate: new Date("02/01/2019").toISOString(),
  },
  {
    entityId: 12,
    entityTitle: "Entity 12",
    entityDescription: "Entity 12 in service",
    createDate: new Date("03/01/2019").toISOString(),
    updateDate: new Date("04/01/2019").toISOString(),
  },
  {
    entityId: 13,
    entityTitle: "Entity 13",
    entityDescription: "Entity 13 in service",
    createDate: new Date("05/01/2019").toISOString(),
    updateDate: new Date("06/01/2019").toISOString(),
  },
  {
    entityId: 14,
    entityTitle: "Entity 14",
    entityDescription: "Entity 14 in service",
    createDate: new Date("07/01/2019").toISOString(),
    updateDate: new Date("08/01/2019").toISOString(),
  },
  {
    entityId: 15,
    entityTitle: "Entity 15",
    entityDescription: "Entity 15 in service",
    createDate: new Date().toISOString(),
    updateDate: new Date().toISOString(),
  },
  {
    entityId: 16,
    entityTitle: "Entity 16",
    entityDescription: "Entity 16 in service",
    createDate: new Date("01/01/2019").toISOString(),
    updateDate: new Date("02/01/2019").toISOString(),
  },
  {
    entityId: 17,
    entityTitle: "Entity 17",
    entityDescription: "Entity 17 in service",
    createDate: new Date("03/01/2019").toISOString(),
    updateDate: new Date("04/01/2019").toISOString(),
  },
  {
    entityId: 18,
    entityTitle: "Entity 18",
    entityDescription: "Entity 18 in service",
    createDate: new Date("05/01/2019").toISOString(),
    updateDate: new Date("06/01/2019").toISOString(),
  },
  {
    entityId: 19,
    entityTitle: "Entity 19",
    entityDescription: "Entity 19 in service",
    createDate: new Date("07/01/2019").toISOString(),
    updateDate: new Date("08/01/2019").toISOString(),
  },
  {
    entityId: 20,
    entityTitle: "Entity 20",
    entityDescription: "Entity 20 in service",
    createDate: new Date("07/01/2019").toISOString(),
    updateDate: new Date("08/01/2019").toISOString(),
  }
];

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
