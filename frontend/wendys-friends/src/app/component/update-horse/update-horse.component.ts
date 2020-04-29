import {Component, OnInit} from '@angular/core';
import {Input} from '@angular/core';
import {Output, EventEmitter} from '@angular/core';
import {HorseService} from '../../service/horse.service';
import {Horse} from '../../dto/horse';
import {HorseValidationError} from '../../errors/horseValidationError';
import {ServerError} from '../../errors/serverError';
import {SimpleError} from '../../errors/simpleError';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';

@Component({
  selector: 'app-update-horse',
  templateUrl: './update-horse.component.html',
  styleUrls: ['./update-horse.component.scss']
})
export class UpdateHorseComponent implements OnInit {
  validationError = new HorseValidationError();
  serverError = new ServerError();
  ownerset = false;
  ownerMessage='';
  @Input() updateHorseModel: Horse;
  @Input() raceOptions;
  @Input() ratingOptions;
  @Output() toSearchMode = new EventEmitter();
  changeOwner = false;
  imageError= new SimpleError();
  sanitizedImageSrc: SafeResourceUrl;

  constructor(private horseService: HorseService, private sanitizer: DomSanitizer) {
  }

  ngOnInit(): void {
    this.setImgUrl();
  }

  setImgUrl(){
    if(this.updateHorseModel.imageBase64!==null){
      this.sanitizedImageSrc=this.sanitizer.bypassSecurityTrustUrl('data:image/'+'jpeg'+';base64,'+this.updateHorseModel.imageBase64);
      this.imageError.saved=true;
    }
  }

  /**
   * activates owner search
   */
  onChooseOwner() {
    this.changeOwner = !this.changeOwner;
    this.ownerset = false;
  }

  /**
   * sets the ownerid to @param id
   */
  onSetOwner(id: number) {
    this.ownerset = true;
    this.changeOwner = false;
    this.updateHorseModel.idofowner = id;
    this.ownerMessage = 'Owner set!';
  }

  /**
   * Sends the horse to be updated to the server
   */
  onUpdateHorse = () => {
    this.validationError.error = false;
    this.validationError.validate(this.updateHorseModel);
    if (!this.validationError.error) {
      console.log('Update horse.');
      this.horseService.updateHorse(this.updateHorseModel).subscribe(
        (response: any) => {
          console.log(response);
          this.toSearchMode.emit();
        },
        (error: any) => {
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
        this.imageError.errorMessage = 'Only Images of type JPG are allowed!';
        return false;
      }
      const fileReader = new FileReader();
      fileReader.onload = (e: any) => {
        this.updateHorseModel.imageBase64 = e.target.result.replace('data:image/jpeg;base64,','');
        this.imageError.saved=true;
        this.setImgUrl();
      }
      fileReader.onerror = (error: any) => {
        this.imageError.error = true;
        this.imageError.errorMessage = 'Error during loading image!'
      }
      fileReader.readAsDataURL(image.target.files[0]);
      console.log('image uploaded');
    }
  }

  removeImage(){
    this.imageError.saved=false;
    this.updateHorseModel.imageBase64=null;
  }

}
