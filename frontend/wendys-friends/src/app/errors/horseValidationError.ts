import {Horse} from '../dto/horse';

export class HorseValidationError {
  error: boolean;
  errorMessage: string;

  constructor() {
    this.error = false;
    this.errorMessage = '';
  }

  vanishError = () => {
    this.error = false;
    this.errorMessage = '';
  }

  validate = (horse: Horse) => {
    if (horse.name === null || horse.name === '') {
      this.error = true;
      this.errorMessage = 'Please specify the name of the horse!';
    } else if (!horse.birthdate) {
      this.error = true;
      this.errorMessage = 'Please specify the birth-date for the horse!';
    } else if (!horse.rating) {
      this.error = true;
      this.errorMessage = 'Please specify the rating for the horse!';
    } else if (!horse.race) {
      this.error = true;
      this.errorMessage = 'Please specify the race for the horse!';
    } else if (!horse.idofowner) {
      this.error = true;
      this.errorMessage = 'Please choose the owner of the horse!';
    }
  }
}
