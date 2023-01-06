// table 
$.ajax({
  url: "https://pokeapi.co/api/v2/pokemon/",
  success: function (res) {
    console.log(res.results);
    text = "";
    $.each(res.results, function (key, val) {
      text += `<tr class = "fs-5">
        <td>${key + 1}</td>
        <td class = "text-capitalize text">${val.name}</td>
        <td>
        <button type="button" onclick="detail('${val.url}')" class="text-capitalize text btn btn-outline-warning" data-bs-toggle="modal" data-bs-target="#staticBackdrop" data-target="#poDetails">
        ${val.name}
        </button>
        </td>
      </tr>`;
    });
    $("#tbodyP").html(text);
  },
  error: function(jqXHR, textStatus, errorThrown) {
    console.log(jqXHR, textStatus, errorThrown)},
});

// modal
function loc(location_area_encounters){
  $.ajax({
    url: location_area_encounters,
    success: function(res){
      $('.loc').html(res[1].location_area.name)
      console.log(res);
    }
  })
}

function habitat(url){
  $.ajax({
    url: url,
    success: function(res){
      console.log(res.habitat);
      $('.habitat').html(res.habitat.name)
      console.log(res);
    }});
}

function detail(url) {
  $.ajax({
    url: url,
    success: function (res){
      $('.img-pokemon').attr(
        'src',
        `${res.sprites.other['official-artwork'].front_default}`
      )

      $('.species').html(res.name)
      $('.sizew').html(res.weight)
      $('.sizeh').html(res.height)
      $('.baseexp').html(res.base_experience)
      $('.loc').html(loc(res.location_area_encounters))
      $('.habitat').html(habitat(res.species.url))

      $('.hp').attr('style', `width:${res.stats[0].base_stat}%`)
      $('.hp').html(`Heat Point: ${res.stats[0].base_stat}%`)

      $('.attack').attr('style', `width:${res.stats[1].base_stat}%`)
      $('.attack').html(`Attack :  ${res.stats[1].base_stat}%`)

      $('.defense').attr('style', `width:${res.stats[2].base_stat}%`)
      $('.defense').html(`Defense :  ${res.stats[2].base_stat}%`)

      $('.specialattack').attr('style', `width:${res.stats[3].base_stat}%`)
      $('.specialattack').html(`Spec.Attack :  ${res.stats[3].base_stat}%`)

      $('.specialdefense').attr('style', `width:${res.stats[4].base_stat}%`)
      $('.specialdefense').html(`Spec.Defense :  ${res.stats[4].base_stat}%`)

      $('.speed').attr('style', `width:${res.stats[5].base_stat}%`)
      $('.speed').html(`Speed :  ${res.stats[5].base_stat}%`)

    text = "";

    $.each(res.abilities, function (key, val) {
      $('.ability').html(val.ability.name);
    });

    $.each(res.types, function (key, val) {
      $('.type').html(val.type.name);
    });
    
    $.each(res.moves, function (key, val){
      $('.move').html(val.move.name);
    })

    console.log(res);

    },
    error: function(jqXHR, textStatus, errorThrown) {
      console.log(jqXHR, textStatus, errorThrown)},
  });
}

//radar
const config = new Chart({
  type: 'radar',
  data: data,
  options: {
    elements: {
      line: {
        borderWidth: 3
      }
    }
  },
});

const data = {
  labels: [
    "Heat Point",
    "Attack",
    "Defense",
    "Speed"
  ],
  datasets: [{
    label: "Base Stat",
    data: [`${res.stats[0].base_stat}`, `${res.stats[1].base_stat}`, `${res.stats[2].base_stat}`, `${res.stats[5].base_stat}`],
    fill: true,
    backgroundColor: 'rgba(255, 99, 132, 0.2)',
    borderColor: 'rgb(255, 99, 132)',
    pointBackgroundColor: 'rgb(255, 99, 132)',
    pointBorderColor: '#fff',
    pointHoverBackgroundColor: '#fff',
    pointHoverBorderColor: 'rgb(255, 99, 132)'
  }]
};

