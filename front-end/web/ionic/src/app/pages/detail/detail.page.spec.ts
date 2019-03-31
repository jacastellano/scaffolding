import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';

import { IonicModule } from '@ionic/angular';

import { TranslateModule, TranslateStore } from '@ngx-translate/core';

import { DetailPage } from './detail.page';
import { routes } from '../../app-routing.module';

describe('DetailPage', () => {
  let component: DetailPage;
  let fixture: ComponentFixture<DetailPage>;
  let router: Router;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [DetailPage],
      imports: [
        TranslateModule.forChild(),
        ReactiveFormsModule,
        RouterTestingModule.withRoutes(routes),
        IonicModule
      ],
      providers: [TranslateStore, HttpClient, HttpHandler],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
      .compileComponents();
  }));

  beforeEach(() => {
    router = TestBed.get(Router);
    fixture = TestBed.createComponent(DetailPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
