# TestAnimMaster

//简述----------------------------------------------------------------------------------------------------------------------------------

View Animation
特点简单
    支持简单的缩放、平移、旋转、透明度基本的动画
  局限性
    不能做到View有一个颜色的切换动画(value Animation可以通过设置TypeEvaluator完成的工作为动画从起始值到结束值的平滑过渡)
    不能做到可以使用3D旋转动画
    不能做到当动画停止时，View的位置就是当前的位置
    
Property Animation
    1.Duration动画的持续时间，默认300ms。
    2.Time interpolation：时间差值
    3.Repeat count and behavior：重复次数、以及重复模式；可以定义重复多少次；重复时从头开始，还是反向。
    4.Animator sets: 动画集合，你可以定义一组动画，一起执行或者顺序执行。
	5.Frame refresh delay：帧刷新延迟，对于你的动画，多久刷新一次帧；默认为10ms，但最终依赖系统的当前状态；基本不用管。
相关的类
	1.ObjectAnimator  动画的执行类，后面详细介绍
	2.ValueAnimator 动画的执行类，后面详细介绍 
	3.AnimatorSet 用于控制一组动画的执行：线性，一起，每个动画的先后执行等。
	4.AnimatorInflater 用户加载属性动画的xml文件
	5.TypeEvaluator  类型估值，主要用于设置动画操作属性的值。
	6.TimeInterpolator 时间插值，上面已经介绍。   

//ObjectAnimator的使用------------------------------------------------------------------------------------------------------------------
     
  ObjectAnimator.ofFloat(view, "rotationX", 0.0F, 360.0F).setDuration(500).start();  //x轴滑动    rotationX是getPropName反射获取
     //不使用animset完成控制动画的多重属性变化
     ObjectAnimator anim = ObjectAnimator.ofFloat(view, "zhy", 1.0F,  0.0F).setDuration(500);//设置属性的那个字符串，随便写一个该对象没有的属性(AnimatorUpdateListener必须实现用来控制对象的属性变化)
    anim.start();  
    anim.addUpdateListener(new AnimatorUpdateListener() {  
        @Override  
        public void onAnimationUpdate(ValueAnimator animation)  {  
            float cVal = (Float) animation.getAnimatedValue();  
            view.setAlpha(cVal);  
            view.setScaleX(cVal);  
            view.setScaleY(cVal);  
        }  
    });  
抛物线的效果跟上面的多重属性的调用相似只不过控制的属性不同（已经将例子放在了代码中）
          ball.setTranslationX((1 - cVal) * x * 25);
          ball.setTranslationY((1 - cVal) * x * x * (1 - cVal));
需要注意的点
  animation.getAnimatedValue() 默认返回的内容为0到1的float值（前提没设置TypeEvaluator设置后为TypeEvaluator返回的值
  
 //另一种调用属性动画的方式
     PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);  
     PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);  
     PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);  
     ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();  
     

//ValueAnimator的使用------------------------------------------------------------------------------------------------------------------ 


ValueAnimator跟ObjectAnimator比
 1.ObjectAnimator是继承于ValueAnimator
 2.ValueAnimator 这个类是对值得平滑过渡的动画，什么意思呢。就是对数值在一定时间内进行平滑过渡。ObjectAnimator 与ValueAnimator不同的是，ObjectAnimator是对 对象的属性 进行平滑过渡。
 3.ValueAnimator你可以自己根据当前动画的计算值，来操作任何属性
 
//基本用法
ValueAnimator animator = ValueAnimator.ofFloat(0, mScreenHeight - pao.getHeight());
                animator.setTarget(ball);
                animator.setDuration(3000).start();  
正如生面介绍的需要给属性动画添加AnimatorUpdateListener否则只是数值的改变因为并没有改变对象的任何属性
 animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        ball.setTranslationY((Float) animation.getAnimatedValue());
                    }
                });
 这样的话就可以在回调中处理view的属性了
通过ValueAnimator实现抛物线
//TypeEvaluator完成的工作为动画从起始值到结束值的平滑过渡 fraction代表动画的完成度  startValue动画的起始值  endValue动画的结束值
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override   // fraction = t / duration
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF point = new PointF(); // x方向200px/s ，则y方向0.5 * 10 * t
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();//默认是0-1的完成比例 设置TypeEvaluator后为返回内容的返回值
                ball.setX(point.x);
                ball.setY(point.y);
            }
        });
通过上述代码我们在设置TypeEvaluator（估值器）来控制返回的animation.getAnimatedValue()从而控制动画的属性

通过跟上面的对比我们发现我们可以通过设置TypeEvaluator控制返回内容控制动画的属性同时我们也可以通过直接从animation.getAnimatedValue()获取到的0到1的返回值控制动画的属性两者的选择看自己的喜好


//AnimatorSet--------------------------------------------------------------------------------------------------------------------------

//方案1
 ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall, "scaleX", 1.0f, 2f);  
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall, "scaleY", 1.0f, 2f);  
        AnimatorSet animSet = new AnimatorSet();  
        animSet.setDuration(2000);  
        animSet.setInterpolator(new LinearInterpolator());  
        //两个动画同时执行  
        animSet.playTogether(anim1, anim2);  
        animSet.start();  

 //方案2
 ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall, "scaleX",1.0f, 2f);  
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall, "scaleY",1.0f, 2f);  
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(mBlueBall,"x",  cx ,  0f);  
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(mBlueBall,"x", cx);   
        /** 
         * anim1，anim2,anim3同时执行 
         * anim4接着执行 
         */  
        AnimatorSet animSet = new AnimatorSet();  
        animSet.play(anim1).with(anim2);  
        animSet.play(anim2).with(anim3);  
        animSet.play(anim4).after(anim3);  
        animSet.setDuration(1000);  
        animSet.start(); 
        
第一：使用playTogether两个动画同时执行，当然还有playSequentially依次执行~~
第二：如果我们有一堆动画，如何使用代码控制顺序，比如1，2同时；3在2后面；4在1之前等~就是效果2了

//xml定义动画--------------------------------------------------------------------------------------------------------------------------

  <set xmlns:android="http://schemas.android.com/apk/res/android"  
    android:ordering="together" >  
	    <objectAnimator  
		android:duration="1000"  
		android:propertyName="scaleX"  
		android:valueFrom="1"  
		android:valueTo="0.5" >  
	    </objectAnimator>  
	    <objectAnimator  
		android:duration="1000"  
		android:propertyName="scaleY"  
		android:valueFrom="1"  
		android:valueTo="0.5" >  
	    </objectAnimator>    
  </set>  

// 加载动画  
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.scale);  
        mMv.setPivotX(0);  
        mMv.setPivotY(0);  
        //显示的调用invalidate  
        mMv.invalidate();  
        anim.setTarget(mMv);  
        anim.start();  






















