export class SimpleError {
  error=false;
  errorMessage='';
  saved=false;
  horseImageBase64;
  constructor(){}

  vanishError(){
    this.error=false;
    this.errorMessage='';
  }
}
