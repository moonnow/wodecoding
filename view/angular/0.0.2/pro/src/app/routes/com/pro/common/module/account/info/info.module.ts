import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { InfoRoutingModule } from './info-routing.module';
import { InfoListComponent } from './info-list/info-list.component';
import { InfoEditComponent } from './info-edit/info-edit.component';
import { InfoDetailComponent } from './info-detail/info-detail.component';

const COMPONENTS = [
  InfoListComponent,
  InfoEditComponent,
  InfoDetailComponent
];

const COMPONENT_NOROUNT = [
];

@NgModule({
  imports: [
    SharedModule,
    InfoRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENT_NOROUNT
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class InfoModule { }
