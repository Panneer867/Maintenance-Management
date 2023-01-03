

//************ All Locate By starts *****************

  function initMap() {
        var options = {     
            zoom: 10,
            center: { lat: 13.05772063959846, lng: 77.59725398533946 }
        }
        
          var options2 = {     
            zoom: 10,
            center: { lat: 13.05772063959846, lng: 77.59725398533946 }
        }
        var map = new google.maps.Map(document.getElementById('map'), options);
             var map1 = new google.maps.Map(document.getElementById('todaymap'), options);
        var marker = new google.maps.Marker({
            position: { lat: 13.05772063959846, lng: 77.59725398533946 },
            map: map
        });
         var marker2 = new google.maps.Marker({
            position: { lat: 13.05772063959846, lng: 77.59725398533946 },
            map: map1
        });
    }


//************ All Locate By Ends *****************
