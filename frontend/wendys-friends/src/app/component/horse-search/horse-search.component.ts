import { Component, OnInit } from '@angular/core';
import {Input} from '@angular/core';
import {Output, EventEmitter} from '@angular/core';
import {Horse} from '../../dto/horse';
import {HorseService} from '../../service/horse.service';
import {ServerError} from '../../errors/serverError';


@Component({
  selector: 'app-horse-search',
  templateUrl: './horse-search.component.html',
  styleUrls: ['./horse-search.component.scss']
})
export class HorseSearchComponent implements OnInit {
  serverError = new ServerError();
  searchHorseModel = new Horse(null,null,null,null,null,null,null,null, null, null);
  @Input() raceOptions;
  @Input() ratingOptions;
  @Output() updateMode = new EventEmitter<Horse>();
  horses: Horse[];

  constructor(private horseService: HorseService) { }

  ngOnInit(): void {
  }

  onShowAll=()=>{
    this.searchHorseModel.name='';
    this.onSearchHorse();
  }

  onSearchHorse = () => {
    console.log('Search horse.');
    this.horseService.searchHorse(this.searchHorseModel).subscribe(
      (horses: Horse[]) => {
        this.horses = horses;
        this.serverError.vanishError();
        console.log(JSON.stringify(this.horses));
      },
      (error: any) => {
        this.serverError.errorHandling(error);
      }
    );
  }

  /**
   * Deletes a horse
   */
  onDeleteHorse = (horse: Horse) => {
    console.log('Delete horse.');
    const i = this.horses.indexOf(horse);
    this.horseService.deleteHorse(horse.id).subscribe(
      (response: any) => {
        this.horses.splice(i, 1);
        console.log(response)

      },
      (error: any) => {
        this.serverError.errorHandling(error);
      }
    );
  }

}
