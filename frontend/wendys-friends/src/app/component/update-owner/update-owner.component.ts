import { Component, OnInit } from '@angular/core';
import {Input} from '@angular/core';
import {Output, EventEmitter} from '@angular/core';
import {OwnerService} from '../../service/owner.service';
import {ServerError} from '../../errors/serverError';

@Component({
  selector: 'app-update-owner',
  templateUrl: './update-owner.component.html',
  styleUrls: ['./update-owner.component.scss']
})
export class UpdateOwnerComponent implements OnInit {
  serverError = new ServerError();
  @Input() updateOwnerModel;
  @Output() toSearchMode = new EventEmitter();
  constructor(private ownerService: OwnerService) { }

  ngOnInit(): void {
  }

  /**
   * sends updated owner to the server
   */
  onUpdateOwner = () =>{
    console.log('Owner updated.');
    this.ownerService.updateOwner(this.updateOwnerModel).subscribe(
      (response: any) => {
        console.log(response);
        this.toSearchMode.emit();
      },
      (error:any) => {
        this.serverError.errorHandling(error);
      }
    )
  };
}
