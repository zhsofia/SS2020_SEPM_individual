import {Component, OnInit} from '@angular/core';
import {OwnerService} from '../../service/owner.service';
import {Owner} from '../../dto/owner';
import {ServerError} from '../../errors/serverError';
import {SimpleError} from '../../errors/simpleError';


@Component({
  selector: 'app-add-new-owner',
  templateUrl: './add-new-owner.component.html',
  styleUrls: ['./add-new-owner.component.scss']
})
export class AddNewOwnerComponent implements OnInit {
  success = false;
  nameError = new SimpleError();
  serverError = new ServerError();
  addNewOwnerModel = new Owner(null, null, null, null);
  ownerNameEmpty: boolean;

  constructor(private ownerService: OwnerService) {
  }

  ngOnInit(): void {
  }

  /**
   * sends new owner to the server
   */
  onNewOwner = () => {
    this.ownerNameEmpty = this.addNewOwnerModel.name == null || this.addNewOwnerModel.name === '';
    if (!this.ownerNameEmpty) {
      console.log('New owner sent to server');
      console.log(this.addNewOwnerModel);
      this.ownerService.addNewOwner(this.addNewOwnerModel).subscribe(
        (response: any) => {
          this.addNewOwnerModel.name = null;
          this.success = true;
          this.serverError.vanishError();
          this.nameError.vanishError();
          console.log(response)
        },
        (error: any) => {
          this.success = false;
          this.serverError.errorHandling(error);
        }
      );
    }else{
      this.nameError.error=true;
      this.nameError.errorMessage='Name cannot be empty!';
    }
  }
}
