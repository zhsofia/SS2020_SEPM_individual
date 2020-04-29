import {Component, OnInit} from '@angular/core';
import {HorseService} from '../../service/horse.service';
import {OwnerService} from '../../service/owner.service';
import {Horse} from '../../dto/horse';

@Component({
  selector: 'app-horse',
  templateUrl: './horse.component.html',
  styleUrls: ['./horse.component.scss']
})
export class HorseComponent implements OnInit {
  updatemode = false;
  searchmode = true;
  addmode = false;
  ratingOptions = ['1', '2', '3', '4', '5'];
  raceOptions = ['ARABIAN', 'MORGAN', 'PAINT', 'APPALOOSA'];
  updateHorseModel = new Horse(null,null, null, null, null, null, null, null, null, null);


  constructor() {
  }

  ngOnInit(): void {
  }

  /**
   * used to switch to searchmode
   */
  activateSearch() {
    this.searchmode = true;
    this.updatemode = false;
    this.addmode = false;
  }

  /**
   * used to switch to updatemode
   */
  activateUpdate(horse: Horse) {
    this.updateHorseModel = horse;
    this.searchmode = false;
    this.updatemode = true;
    this.addmode = false;
  }

  /**
   * used to switch to searchmode
   */
  activateAdd() {
    this.searchmode = false;
    this.updatemode = false;
    this.addmode = true;
  }
}
