export class ServerError {
  error: boolean;
  errorMessage: string;
  constructor(){
    this.error=false;
    this.errorMessage='';
  }

  vanishError = () => {
    this.error=false;
    this.errorMessage='';
  }

  errorHandling = (error: any) => {
    console.log(error);
    this.error = true;
    if (error.status === 0) {
      // If status is 0, the backend is probably down
      this.errorMessage = 'The backend seems not to be reachable';
    } else if (error.error.message === 'No message available') {
      // If no detailed error message is provided, fall back to the simple error name
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error.message;
    }
  }
}
