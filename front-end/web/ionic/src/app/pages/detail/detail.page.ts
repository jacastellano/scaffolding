import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Entity } from 'src/app/models/entity.model';
import { EntityService } from '../../services/entity.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.page.html',
  styleUrls: ['./detail.page.scss'],
})
export class DetailPage implements OnInit {

  entityId: string;
  formMode: string;
  entityForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private service: EntityService) { }

  get f() { return this.entityForm.controls; }

  onSubmit() {
    if (this.entityForm.invalid) {
      return;
    }
    if (this.formMode === 'CREATE') {
      this.createEntity(this.entityForm.value);
    } else {
      this.updateEntity(this.entityForm.value);
    }
  }

  onCancel() {
    this.router.navigateByUrl('/list');
  }

  createEntity(formValue: any) {

    const newEntity: Entity = {
      entityTitle: formValue.title,
      entityDescription: formValue.description,
    };

    this.service.createEntity(newEntity)
      .subscribe(
        (response) => {
          const data = response.body;
          console.log('createEntity:OK:' + data);
          this.router.navigateByUrl('/list');
        },
        (error) => {
          console.log('createEntity:ERROR: ' + error);
        });
  }

  updateEntity(formValue: any) {

    const updatedEntity: Entity = {
      entityTitle: formValue.title,
      entityDescription: formValue.description,
    };

    this.service.updateEntity(updatedEntity, this.entityId)
      .subscribe(
        (response) => {
          const data = response.body;
          console.log('updateEntity:OK' + data);
          this.router.navigateByUrl('/list');
        },
        (error) => {
          console.log('updateEntity:ERROR: ' + error);
        });
  }

  ngOnInit(): void {

    this.entityId = this.route.snapshot.paramMap.get('id');

    this.entityForm = this.formBuilder.group({
      entityId: '',
      title: ['', Validators.required],
      description: '',
      createDate: '',
      updateDate: '',
    });

    if (!this.entityId) {
      this.formMode = 'CREATE';
    } else {
      this.formMode = 'EDIT';
    }

    if (this.formMode === 'EDIT') {
      this.service.findEntityById(this.entityId)
        .subscribe(
          (response) => {
            const data: Entity = this.convertToEntity(response.body);
            this.entityForm.setValue({
              entityId: data.entityId,
              title: data.entityTitle,
              description: data.entityDescription,
              createDate: data.createDate,
              updateDate: data.updateDate
            });
          },
          (error) => {
            console.log('findEntityById:ERROR: ' + error);
          });
    }

  }

  convertToEntity(data: any): Entity {

    const createDate = new Date(data.createDate);
    const updateDate = new Date(data.updateDate ? data.updateDate : data.createDate);

    const entity: Entity = {
      ...data,
      createDate: createDate.toISOString(),
      updateDate: updateDate.toISOString(),
    };

    return entity;
  }

}
