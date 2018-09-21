import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { HuihuaRoutingModule } from './huihua-routing.module';
import { HuihuaListComponent } from './huihua-list/huihua-list.component';
import { HuihuaEditComponent } from './huihua-edit/huihua-edit.component';
import { HuihuaDetailComponent } from './huihua-detail/huihua-detail.component';

const COMPONENTS = [
  HuihuaListComponent,
  HuihuaEditComponent,
  HuihuaDetailComponent
];

const COMPONENT_NOROUNT = [
];

@NgModule({
  imports: [
    SharedModule,
    HuihuaRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENT_NOROUNT
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class HuihuaModule { }
