import {Component, OnInit} from '@angular/core';
import { Input } from '@angular/core';
import {Owner} from '../../dto/owner';
import {Horse} from '../../dto/horse';
import {Output, EventEmitter} from '@angular/core';
import {OwnerService} from '../../service/owner.service';
import {DomSanitizer, SafeResourceUrl} from '@angular/platform-browser';


@Component({
  selector: 'app-search-res-element-horse',
  templateUrl: './search-res-element-horse.component.html',
  styleUrls: ['./search-res-element-horse.component.scss']
})
export class SearchResElementHorseComponent implements OnInit {
  @Input() horse: Horse;
  @Output() deleteHorse=new EventEmitter<Horse>();
  @Output() updateMode=new EventEmitter<Horse>();
  owner: Owner;
  noOwner=false;
  sanitizedImageSrc: SafeResourceUrl;
  image: boolean;
  constructor(private ownerService: OwnerService, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.image=this.horse.imageBase64!==null
    if(this.image){
    this.sanitizedImageSrc=this.sanitizer.bypassSecurityTrustUrl('data:image/'+'jpeg'+';base64,'+this.horse.imageBase64);
    }
  }


  searchOwner = (idofOwner: number) => {
    this.ownerService.getOwnerById(idofOwner).subscribe(
      (owner: Owner) =>{
        this.owner=owner;
      },
      error => {
        this.noOwner=true;
      }
    )
  }
}
