package cn.landi.playandroid.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.landi.playandroid.R;
import cn.landi.playandroid.constant.RouteUrl;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/4/12
 * @edit TODO
 */
@Route(path = RouteUrl.STEPVIEW_ACTIVITY)
public class StepViewActivity extends AppCompatActivity {


    @BindView(R.id.toobar)
    Toolbar toobar;
    @BindView(R.id.step_view)
    HorizontalStepView stepView;
    @BindView(R.id.btn_next)
    Button btnNext;

    List<StepBean> stepsBeanList = new ArrayList<>();
    @BindView(R.id.step_view2)
    StepView stepView2;
    @BindView(R.id.btn_next2)
    Button btnNext2;
    private int nCurrentStep = 0, nStep=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stepview);
        ButterKnife.bind(this);

        initToolbar();
        initStepView();
        initStepView2();
//        stepView2.setSteps();
    }

    private void initToolbar() {
        setSupportActionBar(toobar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toobar.setTitle("流程图");
        toobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initStepView() {

        StepBean stepBean0 = new StepBean("接单", 0);
        StepBean stepBean1 = new StepBean("打包", -1);
        StepBean stepBean2 = new StepBean("出发", -1);
        StepBean stepBean3 = new StepBean("送单", -1);
        StepBean stepBean4 = new StepBean("完成", -1);
//        StepBean stepBean5 = new StepBean("接单", -1);
//        StepBean stepBean6 = new StepBean("打包", -1);
//        StepBean stepBean7 = new StepBean("出发", -1);
//        StepBean stepBean8 = new StepBean("送单", -1);
//        StepBean stepBean9 = new StepBean("完成", -1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);
        stepsBeanList.add(stepBean4);
//        stepsBeanList.add(stepBean5);
//        stepsBeanList.add(stepBean6);
//        stepsBeanList.add(stepBean7);
//        stepsBeanList.add(stepBean8);
//        stepsBeanList.add(stepBean9);

        stepView
                .setStepViewTexts(stepsBeanList)//总步骤
                .setTextSize(16)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, android.R.color.white))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, android.R.color.white))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.complted))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.attention));//设置StepsViewIndicator AttentionIcon


    }

    private void initStepView2() {
        stepView2.getState()
//                .selectedTextColor(ContextCompat.getColor(this, R.color.colorAccent))
                .animationType(StepView.ANIMATION_CIRCLE)
//                .selectedCircleColor(ContextCompat.getColor(this, R.color.colorAccent))
//                .selectedCircleRadius(getResources().getDimensionPixelSize(R.dimen.dp14))
//                .selectedStepNumberColor(ContextCompat.getColor(this, R.color.colorPrimary))
                // You should specify only stepsNumber or steps array of strings.
                // In case you specify both steps array is chosen.
                .steps(new ArrayList<String>() {{
                    add("身份核验");
                    add("资料填写");
                    add("确认提交");
                    add("完成提交");
                }})
//                .stepsNumber(5)
//                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
//                .stepLineWidth(getResources().getDimensionPixelSize(R.dimen.dp1))
//                .textSize(getResources().getDimensionPixelSize(R.dimen.sp14))
//                .stepNumberTextSize(getResources().getDimensionPixelSize(R.dimen.sp16))
//                .typeface(ResourcesCompat.getFont(this, R.font.roboto_italic))
                // other state methods are equal to the corresponding xml attributes
                .commit();
    }

    @OnClick({R.id.btn_next, R.id.btn_next2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                stepsBeanList.get(nCurrentStep).setState(1);
                if (nCurrentStep < stepsBeanList.size() - 1) {
                    nCurrentStep++;
                    stepsBeanList.get(nCurrentStep).setState(0);
                }
                stepView.setStepViewTexts(stepsBeanList);
                break;
            case R.id.btn_next2:
                stepView2.go(++nStep, true);
                if (nStep == 3) {
                    stepView2.done(true);
                }
                break;
        }
    }
}
