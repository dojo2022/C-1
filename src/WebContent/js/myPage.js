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
  color: '#FFEA82',
  trailColor: '#eee',//ゲージベースの色
  trailWidth: 1,//ゲージベースの太さ
  svgStyle: {width: '100%', height: '100%'}
});




//xにDBのカウントの値を入れる
let x

//かっこの中身が?/100。
bar.animate(100/100);
