import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { VilleService } from './../../../services/ville.service';
import { VilleDto } from './../../../models/ville';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-ville',
  templateUrl: './create-ville.component.html',
  styleUrls: ['./create-ville.component.scss']
})
export class CreateVilleComponent implements OnInit {

  formDataVilleDTO: VilleDto = new VilleDto();

  data;
  paramId :any = 0;
  mySubscription: any;

  constructor(private villeService: VilleService,
              private actRoute: ActivatedRoute,
              private router: Router,
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
      this.getVilleDTOById(this.paramId);
    }

  }

  getVilleDTOById(id: number) {
    console.log('getOne');
    this.villeService.getVilleDTOById(id).subscribe(
      (response: VilleDto) => {
        console.log('data--', response);
        this.formDataVilleDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }


  public onAddVille() {
    this.villeService.addVilleDto(this.formDataVilleDTO).subscribe(
      (response: VilleDto) => {
        alert('Ville created');
        this.router.navigate(['/admin/accueil/villes']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateVille() {
    this.villeService.updateVilleDto(this.formDataVilleDTO.id, this.formDataVilleDTO).subscribe(
      (response: VilleDto) => {
        this.router.navigate(['/backend/admin/villes']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
