import { Injectable } from '@angular/core';

import { HTTP } from '@ionic-native/http/ngx';

import { Entity } from '../models/entity.model';

const ENTITY_SERVICE_URL: string = 'http://10.0.2.2:8080/entities';

@Injectable({
  providedIn: 'root'
})
export class EntityService {

  constructor(private http: HTTP) { }

  findEntityById(id: string) {
    const url: string = `${ENTITY_SERVICE_URL}/${id}`;
    return this.http.get(url, {}, {});
  }

  findAllEntities() {
    const url: string = `${ENTITY_SERVICE_URL}`;
    return this.http.get(url, {}, {});
  }

  createEntity(entity: Entity) {
    const url: string = `${ENTITY_SERVICE_URL}`;
    return this.http.post(url, entity, {});
  }

  updateEntity(entity: Entity, id: number) {
    const url: string = `${ENTITY_SERVICE_URL}/${id}`;
    return this.http.put(url, entity, {});
  }

  deleteEntity(id: number) {
    const url: string = `${ENTITY_SERVICE_URL}/${id}`;
    return this.http.delete(url, {}, {});
  }

}
