import { Component, OnInit, Inject } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LocalityService } from './../../../services/locality.service';
import { Locality, AddresseDto } from './../../../models/locality';


@Component({
  selector: 'app-create-locality',
  templateUrl: './create-locality.component.html',
  styleUrls: ['./create-locality.component.scss']
})
export class CreateLocalityComponent implements OnInit {

  formDataLocalityDTO: AddresseDto = new AddresseDto();
  deleteLocalityDTO: AddresseDto;

  data;
  paramId :any = 0;
  mySubscription: any;

  constructor(private localiteService: LocalityService,
              private router: Router,
              private toastr: ToastrService,
              public dialog: MatDialog,
              private actRoute: ActivatedRoute,
  ){
     //--for reload componant
     this.router.routeReuseStrategy.shouldReuseRoute = () => false;
     this.mySubscription = this.router.events.subscribe((event) => {
       if (event instanceof NavigationEnd) {
         // Trick the Router into believing it's last link wasn't previously loaded
         this.router.navigated = false;
       }
     });
  }

  ngOnInit(): void {
    this.paramId = this.actRoute.snapshot.paramMap.get('id');
    console.log('Param--', this.paramId);
    if(this.paramId  && this.paramId  > 0){
      this.getLocalityDTOById(this.paramId);
    }
  }

  getLocalityDTOById(id: number) {
    console.log('getOne');
    this.localiteService.getLocalityDTOById(id).subscribe(
      (response: AddresseDto) => {
        console.log('data--', response);
        this.formDataLocalityDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  public onAddLocality() {
    this.localiteService.addLocalityDTO(this.formDataLocalityDTO).subscribe(
      (response: AddresseDto) => {
  //      this.dialogRef.close();
        this.toastr.success("Addresse Ajouté avec Succès");
        this.router.navigate(['/backend/admin/localities']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateLocality() {
    this.localiteService.updateLocalityDTO(this.formDataLocalityDTO.id, this.formDataLocalityDTO).subscribe(
      (response: AddresseDto) => {
  //      this.dialogRef.close();
        this.toastr.warning("Addresse Update avec Succès");
        this.router.navigate(['/backend/admin/localities']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
