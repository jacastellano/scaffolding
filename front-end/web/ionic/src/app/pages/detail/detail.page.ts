import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Entity } from 'src/app/models/entity.model';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.page.html',
  styleUrls: ['./detail.page.scss'],
})
export class DetailPage implements OnInit {

  entityForm: FormGroup;
  formMode: string;
  entity: Entity;

  constructor(private formBuilder: FormBuilder) { }

  get f() { return this.entityForm.controls; }

  onSubmit() {
    
    if (this.entityForm.invalid) {
      return;
    }

    if (this.formMode==="CREATE") {
      console.log('CREATE ENTITY:\n\n' + JSON.stringify(this.entityForm.value));
    } else if (this.formMode==="EDIT") {
      console.log('EDIT ENTITY:\n\n' + JSON.stringify(this.entityForm.value));
    } else if (this.formMode==="VIEW") {
      console.log('CLOSE');
    }
  }

  ngOnInit(): void {

    this.formMode = "CREATE";
    /*this.entity = {
      entityId: 2,
      entityTitle: 'Ionic Entity',
      entityDescription: 'Ionic Entity to testing',
      createDate: new Date().toISOString(),
      updateDate: new Date().toISOString(),
    };*/

    this.entityForm = this.formBuilder.group({
      entityId: this.entity ? this.entity.entityId : '',
      title: [this.entity ? this.entity.entityTitle : '', Validators.required],
      description: this.entity ? this.entity.entityDescription : '',
      createDate: this.entity ? this.entity.createDate : '',
      updateDate: this.entity ? this.entity.updateDate : '',
    });
  }

  onBlur() {
    console.log('onblur');
  }

}
