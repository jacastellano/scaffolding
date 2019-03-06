import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Entity } from '../models/entity.model';

const ENTITY_SERVICE_URL = '"http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class EntityService {

  headers: HttpHeaders;

  constructor(private http: HttpClient) { }

  findEntityById(id: number) {
    return this.http.get(ENTITY_SERVICE_URL + '/entities/' + id);
  }

  findAllEntities() {
    return this.http.get(ENTITY_SERVICE_URL + '/entities');
  }

  createEntity(entity: Entity) {
    return this.http.post(ENTITY_SERVICE_URL + '/entities', entity);
  }

  updateEntity(entity: Entity, id: number) {
    return this.http.put(ENTITY_SERVICE_URL + '/entities/' + id, entity);
  }

  deleteEntity(id: number) {
    return this.http.delete(ENTITY_SERVICE_URL + '/entities/' + id);
  }

}