import { Component, OnInit } from '@angular/core';
import {Input} from '@angular/core';
import {Output, EventEmitter} from '@angular/core';
import {Owner} from '../../dto/owner';
import {OwnerService} from '../../service/owner.service';
import {ServerError} from '../../errors/serverError';

@Component({
  selector: 'app-owner-search',
  templateUrl: './owner-search.component.html',
  styleUrls: ['./owner-search.component.scss']
})
export class OwnerSearchComponent implements OnInit {
  searchOwnerModel = new Owner(null, null, null, null);
  @Input() updatemode;
  @Output() setOwner = new EventEmitter<number>();
  @Output() updateOwner = new EventEmitter<Owner>();
  owners: Owner[];
  serverError = new ServerError();

  constructor(private ownerService: OwnerService) {
  }

  ngOnInit(): void {
  }

  onShowAll(){
    this.searchOwnerModel.name='';
    this.onSearchOwners();
  }

  onSearchOwners() {
    console.log('search for owners with name ' + this.searchOwnerModel.name);
    this.ownerService.searchOwner(this.searchOwnerModel.name).subscribe(
      (owners: Owner[]) => {
        console.log(owners[0]);
        this.serverError.vanishError();
        this.owners = owners;
      },
      (error: any) => {
        this.serverError.errorHandling(error);
      }
    )
  }

  /**
   * sends id of the owner to be deleted to the server
   */
  onDeleteOwner = (owner: Owner) => {
    console.log('delete owner with id ' + owner);
    const i = this.owners.indexOf(owner);
    this.ownerService.deleteOwner(owner.id).subscribe(
      (response: any) => {
        console.log(response)
        this.owners.splice(i, 1);
      },
      (error: any) => {
        this.serverError.errorHandling(error);
      }
    )

  }
}
