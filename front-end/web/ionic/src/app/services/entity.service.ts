import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/internal/Observable';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

import { Entity } from '../models/entity.model';

const ENTITY_SERVICE_URL = 'http://localhost:8080/entities';

@Injectable({
  providedIn: 'root'
})
export class EntityService {

  constructor(private http: HttpClient) { }

  findEntityById(id: number): Observable<HttpResponse<Entity>> {

    const url = `${ENTITY_SERVICE_URL}/${id}`;

    return this.http.get<Entity>(
      url,
      { observe: 'response' }
    ).pipe(
      catchError(this.handleError)
    );
  }

  findAllEntities(): Observable<HttpResponse<Entity[]>> {

    const url = `${ENTITY_SERVICE_URL}`;

    return this.http.get<Entity[]>(
      url,
      { observe: 'response' }
    ).pipe(
      catchError(this.handleError)
    );
  }

  createEntity(entity: Entity): Observable<HttpResponse<Entity>> {

    const url = `${ENTITY_SERVICE_URL}`;

    return this.http.post<Entity>(
      url,
      entity,
      { observe: 'response' }
    ).pipe(
      catchError(this.handleError)
    );
  }

  updateEntity(entity: Entity, id: number): Observable<HttpResponse<Entity>> {

    const url = `${ENTITY_SERVICE_URL}/${id}`;

    return this.http.put<Entity>(
      url,
      entity,
      { observe: 'response' }
    ).pipe(
      catchError(this.handleError)
    );
  }

  deleteEntity(id: number): Observable<HttpResponse<Entity>> {

    const url = `${ENTITY_SERVICE_URL}/${id}`;

    return this.http.delete<Entity>(
      url,
      { observe: 'response' }
    ).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with the error
    return throwError(error);
  }

}
