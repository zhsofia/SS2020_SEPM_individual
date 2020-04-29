import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Input} from '@angular/core';
import {HorseService} from '../../service/horse.service';
import {Horse} from '../../dto/horse';
import {HorseValidationError} from '../../errors/horseValidationError';
import {ServerError} from '../../errors/serverError';
import {SimpleError} from '../../errors/simpleError';

@Component({
  selector: 'app-add-new-horse',
  templateUrl: './add-new-horse.component.html',
  styleUrls: ['./add-new-horse.component.scss']
})
export class AddNewHorseComponent implements OnInit {
  success = false;
  serverError = new ServerError();
  validationError = new HorseValidationError();
  addNewHorseModel = new Horse(null, null, null, null, null, null, null, null, null, null);
  @Input() raceOptions;
  @Input() ratingOptions;
  ownerset = false;
  ownerMessage = '';
  chooseOwner = false;
  imageError = new SimpleError();


  constructor(private horseService: HorseService) {
  }

  ngOnInit(): void {
  }

  /**
   * activates owner search
   */
  onChooseOwner() {
    this.chooseOwner = !this.chooseOwner;
    this.ownerset = false;
  }

  onSetOwner(id: number) {
    this.ownerset = true;
    this.chooseOwner = false;
    this.addNewHorseModel.idofowner = id;
    this.ownerMessage = 'Owner set!';
  }

  onRemoveOwner() {
    this.ownerset = true;
    this.ownerMessage = 'No owner!';
    this.addNewHorseModel.idofowner = null;
  }

  /**
   * Sends new horse to server.
   */
  onNewHorse = () => {
    this.validationError.error = false;
    this.validationError.validate(this.addNewHorseModel);
    if (!this.validationError.error) {
      console.log('Send new horse to server.');
      this.horseService.addNewHorse(this.addNewHorseModel).subscribe(
        (response: any) => {
          this.success = true;
          this.imageError.saved=false;
          this.addNewHorseModel = new Horse(null, null, null, null, null, null, null, null, null, null);
          this.ownerset = false;
          this.chooseOwner = false;
          this.serverError.vanishError();
          this.validationError.vanishError();
          console.log(response)
        },
        (error: any) => {
          this.success = false;
          this.serverError.errorHandling(error);
        }
      );
    }
  }

  onUpload(image: any){
    this.imageError.vanishError();
    if(image.target.files && image.target.files[0]) {
      const maxSize = 20000000;
      const allowedTypes = ['image/jpeg'];
      if (image.target.files[0].size > maxSize) {
        this.imageError.error = true;
        this.imageError.errorMessage =
          'Maximum size allowed is ' + maxSize / 1000 + 'Mb';
        return false;
      }

      if (!allowedTypes.includes(image.target.files[0].type)) {
        this.imageError.error = true;
        this.imageError.errorMessage = 'Only Images of type JPG/PNG are allowed';
        return false;
      }
      const fileReader = new FileReader();
      fileReader.onload = (e: any) => {
        this.addNewHorseModel.imageBase64 = e.target.result.replace('data:image/jpeg;base64,','');
        this.imageError.saved=true;
      }
      fileReader.onerror = (error: any) => {
        this.imageError.error = true;
        this.imageError.errorMessage = 'Error during loading image!'
      }
      fileReader.readAsDataURL(image.target.files[0]);
      console.log('image uploaded');
    }
  }
}
