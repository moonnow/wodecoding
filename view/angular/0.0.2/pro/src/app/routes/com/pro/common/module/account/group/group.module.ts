import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { GroupRoutingModule } from './group-routing.module';
import { GroupListComponent } from './group-list/group-list.component';
import { GroupEditComponent } from './group-edit/group-edit.component';
import { GroupDetailComponent } from './group-detail/group-detail.component';

const COMPONENTS = [
  GroupListComponent,
  GroupEditComponent,
  GroupDetailComponent
];

const COMPONENT_NOROUNT = [
];

@NgModule({
  imports: [
    SharedModule,
    GroupRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENT_NOROUNT
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class GroupModule { }
