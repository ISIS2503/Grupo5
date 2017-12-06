console.log(gon.registros_general)
group_data = []

for(var i = 0; i < gon.registros_general_temp.length ; i+= 1) {
  if(group_data[i] !== undefined) {
    group_data[i]['Temperatura'] = gon.registros_general_temp[i].valor
  } else {
    group_data.push({
      y: i,
      Temperatura: 20
    })
  }
}

for(var i = 0; i < gon.registros_general_son.length ; i+= 1) {
  if(group_data[i] !== undefined) {
    group_data[i]['Sonido'] = gon.registros_general_son[i].valor
  } else {
    group_data.push({
      Sonido: 20
    })
  }
}

for(var i = 0; i < gon.registros_general_mon.length ; i+= 1) {
  if(group_data[i] !== undefined) {
    group_data[i]['Monoxido'] = gon.registros_general_mon[i].valor
  } else {
    group_data.push({
      Monoxido: 20
    })
  }
}


for(var i = 0; i < gon.registros_general_luz.length ; i+= 1) {
  if(group_data[i] !== undefined) {
    group_data[i]['Luz'] = gon.registros_general_luz[i].valor
  } else {
    group_data.push({
      Luz: 20
    })
  }
}


console.log(JSON.stringify(group_data))

new Morris.Line({
  // ID of the element in which to draw the chart.
  element: 'general_line_bar',
  // Chart data records -- each entry in this array corresponds to a point on
  // the chart.
  data: group_data,

  xkey: 'y',
  ykeys: ['Temperatura', 'Sonido', "Monoxido", "Luz"],
  labels: ['Series A', 'Series B', 'Series C', 'Series D']
});

new Morris.Donut({
  // ID of the element in which to draw the chart.
  element: 'general_pie_bar',
  // Chart data records -- each entry in this array corresponds to a point on
  // the chart.
  data: [
    { label: 'Temperatura', value: gon.registros_general_temp_all.length },
    { label: 'Sonido', value: gon.registros_general_son_all.length },
    { label: 'Monoxido', value: gon.registros_general_mon_all.length },
    { label: 'Luz', value: gon.registros_general_luz_all.length }
  ],
  labels: ['Value']
});

Morris.Area({
  element: 'general_area_bar',
  data: group_data,
  xkey: 'y',
  ykeys: ['Temperatura', 'Sonido', "Monoxido", "Luz"],
  labels: ['Series A', 'Series B', 'Series C', 'Series D']
});
