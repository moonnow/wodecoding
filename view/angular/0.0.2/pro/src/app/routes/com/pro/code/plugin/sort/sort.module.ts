import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { SortRoutingModule } from './sort-routing.module';
import { SortListComponent } from './sort-list/sort-list.component';
import { SortEditComponent } from './sort-edit/sort-edit.component';
import { SortDetailComponent } from './sort-detail/sort-detail.component';

const COMPONENTS = [
  SortListComponent,
  SortEditComponent,
  SortDetailComponent
];

const COMPONENT_NOROUNT = [
];

@NgModule({
  imports: [
    SharedModule,
    SortRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENT_NOROUNT
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class SortModule { }
