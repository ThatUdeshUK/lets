import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RoutingModule } from './routing.module';
import { MaterialModule } from './material.module';
import { HomeComponent } from './pages/home/home.component';
import { IdeaComponent } from './pages/home/idea/idea.component';
import { HttpClientModule } from '@angular/common/http';
import { AddIdeaComponent } from './pages/home/add-idea/add-idea.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    IdeaComponent,
    AddIdeaComponent
  ],
  entryComponents: [
    AddIdeaComponent
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MaterialModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
