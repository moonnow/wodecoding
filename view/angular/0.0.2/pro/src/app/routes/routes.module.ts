import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { RouteRoutingModule } from './routes-routing.module';

import { UserLoginComponent } from './passport/login/login.component';
import { UserRegisterComponent } from './passport/register/register.component';
import { UserRegisterResultComponent } from './passport/register-result/register-result.component';

import { SystemHomepageComponent } from './com/pro/common/module/homepage/system-homepage/system-homepage.component';
import { PersonalHomepageComponent } from './com/pro/common/module/homepage/personal-homepage/personal-homepage.component';
import { CallbackComponent } from './callback/callback.component';
import { Exception403Component } from './exception/403.component';
import { Exception404Component } from './exception/404.component';
import { Exception500Component } from './exception/500.component';

const COMPONENTS = [
  UserLoginComponent,
  UserRegisterComponent,
  UserRegisterResultComponent,
  SystemHomepageComponent,
  PersonalHomepageComponent,
  CallbackComponent,
  Exception403Component,
  Exception404Component,
  Exception500Component
];
const COMPONENTS_NOROUNT = [];

@NgModule({
  imports: [SharedModule, RouteRoutingModule],
  declarations: [...COMPONENTS, ...COMPONENTS_NOROUNT],
  entryComponents: COMPONENTS_NOROUNT
})
export class RoutesModule {}
