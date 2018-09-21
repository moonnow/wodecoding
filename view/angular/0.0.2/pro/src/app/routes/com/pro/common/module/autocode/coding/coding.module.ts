import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { CodingRoutingModule } from './coding-routing.module';
import { CodingViewComponent } from './coding-view/coding-view.component';

const COMPONENTS = [
  CodingViewComponent
];

const COMPONENT_NOROUNT = [
];

@NgModule({
  imports: [
    SharedModule,
    CodingRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENT_NOROUNT
  ],
  entryComponents: COMPONENT_NOROUNT
})
export class CodingModule { }
