import { Component, OnInit, OnDestroy } from '@angular/core';
import { Idea } from 'src/app/model/Idea';
import { IdeaService } from 'src/app/services/idea.service';
import { MatDialog } from '@angular/material';
import { AddIdeaComponent } from './add-idea/add-idea.component';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {

  ideas: Idea[] = []
  ideaStreamSubscription: Subscription

  constructor(
    private dialog: MatDialog,
    private ideaService: IdeaService
  ) { }

  ngOnInit() {
    this.ideaService.getAll().then(ideas => this.ideas.push(...ideas))
    this.ideaStreamSubscription = this.ideaService.stream().subscribe((event: any) => {
      let idea = JSON.parse(event.data) as Idea
      console.log(idea)
      if (!this.ideas.find(it => it.id == idea.id)) {
        this.ideas.push(idea)
      }
    })
  }

  ngOnDestroy() {
    if (this.ideaStreamSubscription && !this.ideaStreamSubscription.closed)
      this.ideaStreamSubscription.unsubscribe()
  }

  onAddIdea(): void {
    const dialogRef = this.dialog.open(AddIdeaComponent, {
      width: '480px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.ideaService.create(result)
      }
    });
  }

}
