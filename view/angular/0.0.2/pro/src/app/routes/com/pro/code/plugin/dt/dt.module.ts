import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { DtRoutingModule } from './dt-routing.module';
import { DtListComponent } from './dt-list/dt-list.component';
import { DtEditComponent } from './dt-edit/dt-edit.component';
import { DtDetailComponent } from './dt-detail/dt-detail.component';

const COMPONENTS = [
  DtListComponent,
  DtEditComponent,
  DtDetailComponent
];

const COMPONENT_NOROUNT = [
];

@NgModule({
  imports: [
    SharedModule,
    DtRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENT_NOROUNT
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class DtModule { }
