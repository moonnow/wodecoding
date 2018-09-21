import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { DenglurizhiRoutingModule } from './denglurizhi-routing.module';
import { DenglurizhiListComponent } from './denglurizhi-list/denglurizhi-list.component';
import { DenglurizhiEditComponent } from './denglurizhi-edit/denglurizhi-edit.component';
import { DenglurizhiDetailComponent } from './denglurizhi-detail/denglurizhi-detail.component';

const COMPONENTS = [
  DenglurizhiListComponent,
  DenglurizhiEditComponent,
  DenglurizhiDetailComponent
];

const COMPONENT_NOROUNT = [
];

@NgModule({
  imports: [
    SharedModule,
    DenglurizhiRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENT_NOROUNT
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class DenglurizhiModule { }
