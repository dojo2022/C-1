/**
 *
 */

 /*経験値バーのテスト
 →DBからデータを受け取って、200/250みたいな割合で表示させたい。
 */
var bar = new ProgressBar.Line(container, {
  strokeWidth: 4,//進捗ゲージの太さ
  easing: 'easeInOut',//アニメーション効果
  duration: 1400,//時間指定
  color: '#008b8b',
  trailColor: '#eee',//ゲージベースの色
  trailWidth: 1,//ゲージベースの太さ
  svgStyle: {width: '100%', height: '100%'}
});


//xに今日の獲得ポイントを入れる
let x=150;

//かっこの中身が?/100。
if(x<100){
	bar.animate(x/100);
}else{
	while(x>100){
		bar.animate(1);

      x -= x-100
    bar.animate(0);
	}
}


