import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Entity } from '../models/entity.model';

@Injectable({
  providedIn: 'root'
})
export class EntityService {

  headers: HttpHeaders;

  constructor(private http: HttpClient) { }

  findEntityById(id: number): Entity {
    throw new Error("Method not implemented.");
  }

  findAllEntities() {
    return this.http.get("http://localhost:8080/entities");
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
