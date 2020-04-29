import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './component/header/header.component';
import {OwnerComponent} from './component/owner/owner.component';
import {HttpClientModule} from '@angular/common/http';
import { HorseComponent } from './component/horse/horse.component';
import { AddNewHorseComponent } from './component/add-new-horse/add-new-horse.component';
import { FormsModule } from '@angular/forms';
import { AddNewOwnerComponent } from './component/add-new-owner/add-new-owner.component';
import { UpdateOwnerComponent } from './component/update-owner/update-owner.component';
import { UpdateHorseComponent } from './component/update-horse/update-horse.component';
import { OwnerSearchComponent } from './component/owner-search/owner-search.component';
import { HorseSearchComponent } from './component/horse-search/horse-search.component';
import { SearchResElementHorseComponent } from './component/search-res-element-horse/search-res-element-horse.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    OwnerComponent,
    HorseComponent,
    AddNewHorseComponent,
    AddNewOwnerComponent,
    UpdateOwnerComponent,
    UpdateHorseComponent,
    OwnerSearchComponent,
    HorseSearchComponent,
    SearchResElementHorseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
