import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { ColumnsRoutingModule } from './columns-routing.module';
import { ColumnsListComponent } from './columns-list/columns-list.component';
import { ColumnsEditComponent } from './columns-edit/columns-edit.component';
import { ColumnsDetailComponent } from './columns-detail/columns-detail.component';

const COMPONENTS = [
  ColumnsListComponent,
  ColumnsEditComponent,
  ColumnsDetailComponent
];

const COMPONENT_NOROUNT = [
];

@NgModule({
  imports: [
    SharedModule,
    ColumnsRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENT_NOROUNT
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class ColumnsModule { }
