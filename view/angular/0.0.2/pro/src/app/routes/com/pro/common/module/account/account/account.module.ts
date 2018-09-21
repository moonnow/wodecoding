import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { AccountRoutingModule } from './account-routing.module';
import { AccountListComponent } from './account-list/account-list.component';
import { AccountEditComponent } from './account-edit/account-edit.component';
import { AccountDetailComponent } from './account-detail/account-detail.component';

const COMPONENTS = [
  AccountListComponent,
  AccountEditComponent,
  AccountDetailComponent
];

const COMPONENT_NOROUNT = [
];

@NgModule({
  imports: [
    SharedModule,
    AccountRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENT_NOROUNT
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class AccountModule { }
