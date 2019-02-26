import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { TranslateModule } from '@ngx-translate/core';

import { Tab2Page } from './tab2.page';
import { EntityService } from '../services/entity.service';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    RouterModule.forChild([{ path: '', component: Tab2Page },]),
    TranslateModule.forChild(),
  ],
  providers: [EntityService],
  declarations: [Tab2Page]
})
export class Tab2PageModule { }
