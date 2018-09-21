import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { ZhanghaoRoutingModule } from './zhanghao-routing.module';
import { ZhanghaoListComponent } from './zhanghao-list/zhanghao-list.component';
import { ZhanghaoEditComponent } from './zhanghao-edit/zhanghao-edit.component';
import { ZhanghaoDetailComponent } from './zhanghao-detail/zhanghao-detail.component';

const COMPONENTS = [
  ZhanghaoListComponent,
  ZhanghaoEditComponent,
  ZhanghaoDetailComponent
];

const COMPONENT_NOROUNT = [
];

@NgModule({
  imports: [
    SharedModule,
    ZhanghaoRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENT_NOROUNT
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class ZhanghaoModule { }
