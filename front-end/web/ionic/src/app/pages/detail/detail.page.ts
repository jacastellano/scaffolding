import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Entity, convertToEntity } from 'src/app/models/entity.model';
import { EntityService } from '../../services/entity.service';

// TODO Add users managment
// default user code
const USER_CODE = 0;

@Component({
  selector: 'app-detail',
  templateUrl: './detail.page.html',
  styleUrls: ['./detail.page.scss'],
})
export class DetailPage implements OnInit {

  entityId: number;
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
      entityCode: formValue.code,
      entityTitle: formValue.title,
      entityDescription: formValue.description,
      createUser: USER_CODE
    };

    this.service.createEntity(newEntity)
      .subscribe(
        (response) => {
          console.log('createEntity:OK');
          this.router.navigateByUrl('/list');
        },
        (error) => {
          console.log('createEntity:ERROR: ' + error);
        });
  }

  updateEntity(formValue: any) {

    const updatedEntity: Entity = {
      entityId: this.entityId,
      entityCode: formValue.code, 
      entityTitle: formValue.title,
      entityDescription: formValue.description,
      updateUser: USER_CODE
    };

    this.service.updateEntity(updatedEntity, this.entityId)
      .subscribe(
        (response) => {
          this.router.navigateByUrl('/list');
        },
        (error) => {
          console.log('updateEntity:ERROR: ' + error);
        });
  }

  ngOnInit(): void {

    this.entityId = +this.route.snapshot.paramMap.get('id');

    this.entityForm = this.formBuilder.group({
      code: ['', Validators.required],
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
            const data: Entity = convertToEntity(response.body);
            this.entityForm.setValue({
              code: data.entityCode,
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

}
