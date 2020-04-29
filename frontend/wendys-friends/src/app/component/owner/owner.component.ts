import {Component, OnInit} from '@angular/core';
import {Owner} from '../../dto/owner';

@Component({
  selector: 'app-owner',
  templateUrl: './owner.component.html',
  styleUrls: ['./owner.component.scss']
})
export class OwnerComponent implements OnInit {
  updatemode = false;
  searchmode = true;
  addmode = false;
  updateOwnerModel=new Owner(null,null,null,null);

  constructor() {
  }

  ngOnInit() {
  };

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
  activateUpdate(owner: Owner) {
    this.updateOwnerModel = owner;
    this.searchmode = false;
    this.updatemode = true;
    this.addmode = false;
  }

  /**
   * used to switch to addmode
   */
  activateAdd() {
    this.searchmode = false;
    this.updatemode = false;
    this.addmode = true;
  }

}
