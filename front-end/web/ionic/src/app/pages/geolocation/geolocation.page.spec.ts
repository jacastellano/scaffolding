import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClient, HttpHandler } from "@angular/common/http";

import { TranslateModule, TranslateStore } from '@ngx-translate/core';

import { GeolocationPage } from './geolocation.page';

describe('GeolocationPage', () => {
  let component: GeolocationPage;
  let fixture: ComponentFixture<GeolocationPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GeolocationPage ],
      imports: [TranslateModule.forChild()],
      providers: [TranslateStore, HttpClient, HttpHandler],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GeolocationPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
