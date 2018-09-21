import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { QueryRoutingModule } from './query-routing.module';
import { QueryListComponent } from './query-list/query-list.component';
import { QueryEditComponent } from './query-edit/query-edit.component';
import { QueryDetailComponent } from './query-detail/query-detail.component';

const COMPONENTS = [
  QueryListComponent,
  QueryEditComponent,
  QueryDetailComponent
];

const COMPONENT_NOROUNT = [
];

@NgModule({
  imports: [
    SharedModule,
    QueryRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENT_NOROUNT
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class QueryModule { }
