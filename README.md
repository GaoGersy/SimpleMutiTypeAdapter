# SimpleMutiTypeAdapter
##### 一个方法实现RecyclerView多条目类型

>作为一名有经经验的程序猿，你肯定用过各种RecyclerView多条目的框架，但是我猜你应该还没用过这么简单的。

>​						Talk is cheap,show me the GIF!

![效果图](https://github.com/GaoGersy/SimpleMutiTypeAdapter/blob/master/image/Multi.gif)


[SimpleMutiTypeAdapter](https://github.com/GaoGersy/SimpleMutiTypeAdapter)
##### 先来看看使用
###### 首发、一个Bean对应一个layout 或者多个layout对应一个Bean

>先看看注册Bean 和Layout的代码(RecyclerView相关的常规代码省略，你懂的...)：

```
MultiBeanAdapter adapter = new MultiBeanAdapter();
adapter.registerMultiBean(TextMsgBean.class,R.layout.item_right_text);
adapter.registerMultiBean(ImgMsgBean.class,R.layout.item_left_img);
adapter.registerMultiBean(TimeLineBean.class,R.layout.item_time_line);
```
>上Adapter:
```
public class MultiBeanAdapter extends MultiTypeAdapter {
    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        if (item instanceof TextMsgBean) {
            TextMsgBean userBean = (TextMsgBean) item;
            helper.setText(R.id.tv_name, userBean.userName);
        }else if (item instanceof TimeLineBean){
            helper.setText(R.id.tv_name,TimeUtil.getNowDatetime());
        }
    }
}
```
>打完收工 ! 什么 ? 你还没看过瘾 ? 大哥，跟多条目相关的代码真的只有这么多。不信你自己去看Demo....我再怎么掺水你也不会给钱不是！

###### 第二发、一个Bean对应多个Layout。
>看到这里你可能要郁闷了，上面的不是已经解决了多条目的问题了，还写个这个干嘛，多此一举。大兄弟，等你看完了demo的代码，估计你要把大腿都要拍肿了（我靠，原来是这样...）。 杀鸡焉用牛刀，宰牛用杀鸡也干不成活不是?
>真是啰嗦的不行，还不赶快给大爷上代码？不远处漂来一句不耐烦的抱怨声....
>那按例先上注册的代码尝尝鲜：
```
MultiLayoutAdapter adapter = new MultiLayoutAdapter();
adapter.registerMultiLayout(R.layout.item_right_text);
adapter.registerMultiLayout(R.layout.item_left_text);
adapter.registerMultiLayout(R.layout.item_left_img);
adapter.registerMultiLayout(R.layout.item_right_img);
adapter.registerMultiLayout(R.layout.item_left_location);
adapter.registerMultiLayout(R.layout.item_right_location);
adapter.registerMultiLayout(R.layout.item_time_line);
```
>上面一共注册了七种类型的条目，不相信的自己数去...你可能好奇了，居然完全没有看到Bean的影子，此时不禁好奇起来(内部到底是怎么实现的呢？);
>还没等围观群众反应过来，又上来一道开胃菜--Adapter
```
public class MultiLayoutAdapter extends 
        MultiTypeAdapter<IMultiLayout,BaseViewHolder<IMultiLayout>> {

@Override
protected void convert(BaseViewHolder helper, IMultiLayout item) {
        MsgBean bean = (MsgBean) item;
        int type = bean.type;
        if (type==0||type==1){
            helper.setText(R.id.tv_name,bean.msg);
        }else if (type==6){
            helper.setText(R.id.tv_name,TimeUtil.getNowDatetime());
        }
    }
}
```
>不同的bean类：
```
public class MsgBean implements IMultiLayout {
    public boolean left = true;
    public String msg;
    public int type;//0:左边的文字 1：右边的文字 2：左边的图片 3：右边的图片 
                    // 4：左边的定位 5：右边的定位 6：时间线
    int layoutId;
    @Override
    public int getLayoutId() {
        if (type==1){
            return R.layout.item_right_text;
        }else if (type==2){
            return R.layout.item_left_img;
        }else if (type==3){
            return R.layout.item_right_img;
        }else if (type==4){
            return R.layout.item_left_location;
        }else if (type==5){
            return R.layout.item_right_location;
        }else if (type==6){
            return R.layout.item_time_line;
        }
        return R.layout.item_left_text;
    }

    @Override
    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }
}
```
>大家吃完了开胃菜，正等着店家的拿手好戏，只听见店小二嘟囔着说：本店打烊了，大家都请回吧？我了个去，你TM逗我呢？信不信我给你5毛，让你从此翻不了身。
>
>店家一听也慌了神了，只可惜现已黔驴技穷，所有的看家本事都已经用尽。如果真是接了这5毛，以后还有何脸面在这Android阵营中混下去，为此只得将刚才那道开胃菜做法公之于众，以消众怒！
>
>话说到了店家公开祖传秘籍的时刻，气氛那个紧张，大气都不敢出，唯恐错过了什么重头戏...

>只见店家两手一摊，桌上闪落者各式各样的拉丁文，与桌面碰撞发出清脆的声音：
```
public void register(Class clazz, int layoutId) {
        if (checkIsRegistered(clazz)){
            throw new IllegalStateException(clazz + " 已经注册过，请不要重复注册");
        }
        Type type = new Type();
        type.layoutId = layoutId;
        type.itemType = mTypes.size();
        type.clazz = clazz;
        mTypes.add(type);
    }
```
>忽然有人大声喊到：这个我知道，这个就是传说中的注册，是将bean 类的class 和 布局有Id绑定到一起。店家不由微微一笑，向喊话者投去肯定的目光，完全没有理会他刚才的失理。
>店家给小二使了个眼色，小二随即放出了第二批奇怪的文字：
```
public int getItemType(Class clazz) {
        for (int i = 0; i < mTypes.size(); i++) {
            Type type = mTypes.get(i);
            if (type.clazz==clazz){
                return i;
            }
        }
   throw new IllegalStateException(clazz+" 没有注册,请检查..");
 }
```
>大家还在议论纷纷，这时有一个弱弱的声音问到：这个是用来获得Adapter里面的itemType的吗？切，这还用问吗？看名字不就知道了？店家点了点头，弱弱的声音接着问：那是通过Bean的类型来获得itemType的吗？而且itemType实际上就是class的index的值？话音刚落，店家不禁露出了满意的微笑。
```
	public static class Type {
        int itemType;
        int layoutId;
        Class clazz;
        private LayoutInflater inflater;

        public RecyclerView.ViewHolder getViewHolder(ViewGroup parent) {
            if (inflater == null) {
                inflater = LayoutInflater.from(parent.getContext());
            }
            View view = inflater.inflate(layoutId, parent, false);
            return new BaseViewHolder(view);
        }
    }
```
>看到上面闪落的字符，众人皆表示不解，正等着店家分享牛逼的人生经验。只听见店家苦笑着说：其实这样写不是为了装逼，其实是迫于无奈，之前试过很多其它办法没能正常的生产出ViewHolder。听完有人哼了一声，吊我胃口，什么玩意儿？也有人脸上露出微笑，原来店家也不过如此，也食人间烟火的好么！看到店家这样我就放心了...

>大家伙正准备扫兴的散去，忽然店家拿出一大袋的字符，足足有刚才的两倍那么多;说时迟那时快， 只听哐当一声，字符散落了一地;没错，接下来是对MultiLayoutListPool的介绍：
```
public void register(int layoutId) {
        if (checkIsRegistered(layoutId)) {
            throw new IllegalStateException("layoutId = "+layoutId + " 已经注册过，请不要重复注册");
        }
        Type type = new Type();
        type.layoutId = layoutId;
        type.itemType = mTypes.size();
        mTypes.add(type);
    }
```
>众人议论纷纷，为何这里只接收了layoutId，bean呢？被狗吃了？看到大家疑惑的神情，店家发话了：现在只支持一个bean 对应多个layoutId，尚且不支持多个bean对应多个layoutId。所以bean有没有被狗吃掉显得就无关紧要了。
```
    public int getItemType(int layoutId) {
        for (int i = 0; i < mTypes.size(); i++) {
            Type type = mTypes.get(i);
            if (type.layoutId == layoutId) {
                return i;
            }
        }
        throw new IllegalStateException("layoutId = "+layoutId + " 没有注册,请检查..");
    }
```
>PS：这里比较的就是layoutId了，不是class

>看到上面字符有些人不干了(这个跟上面不是一样吗？这还用贴？小编收了5毛，小编凑字数，开启开挂式的吐槽模式);店家没有理会，继续着自己的回忆：
```
public final int getItemViewType(int position) {
        int itemType = -1;
        if (registerType == MULTI_LAYOUT) {
            IMultiLayout item = (IMultiLayout) items.get(position);
            int layoutId = item.getLayoutId();
            itemType = typePool.getItemType(layoutId);
        } else {
            Object item = items.get(position);
            itemType = typePool.getItemType(item.getClass());
        }
        return itemType;
    }
```
>看着上面这简单直白的字符，店家望向远方，沉思起来;论字数和难理解的程度，确定根本算不上什么，解决了的问题都会以字符的形式简简单单的躺在那里，在这过程中的酸甜苦辣别人又如何能体会，甚至几次三番想放弃写一Bean对多layout这种模式。店家回过神来，本打算不作任何解释，后来想想分析下当时自己的思路也是不错的;

>哎呀，我实在编不下去了，还是说人话吧。其实想解决多类型getItemViewType这个可是绕不过的一个坎，谁让recyclerView是根据这里的值的变换来区分各种不同类型的呢？尴尬的是在这个方法里，我们能获取到的资源很少，一个position能干什么大事？能获得当前条目的Bean类，这就够了。对于else下面的还是很好理解的吧，if下面的解决办法确实花了不少时间。首先，我们只有一个Bean,没有其它途径可以知道当前的lauoutId是什么，那么只能在Bean上作文章了。将lauoutId塞给你还不成吗？如果上面的明白了，这里也就不难理解了。哎呀，怎么感觉还是没有完全表达清楚想说的，看来要完全将自己的想法传给听众真是非常难的事。每一个单独的拿出来，感觉都是SO SO。将整个流程连贯起来看会感觉更好些！

###### 参考的项目：

>[MultiType](https://github.com/drakeet/MultiType)  借鉴了实现多条目的思路，实现方式有了很大的不同，有兴趣的可以对照两份代码比较
>[BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper) 借用了里面的BaseViewHolder，方便!

>​	来说说写这个的初衷，写这个之前正在写一个支持多种选择类型的公共组件（有酷炫的动画哟），刚好在网上看到MultiType这个框架，看了下源代码，思路很牛逼的吗，被作者的脑洞惊到了。
>
>为了踏踏实实的体验一把作者的脑洞就引入到正在做的项目中。使用的过程中戳中了我作为懒货的内伤，像我这样的懒货每次都让我写完全程的Viewhoder的代码真的好吗？这功夫又可以打一把王者荣耀了。我相信MultiType作者也是一个勤快的懒货，为什么呢？不懒肯定就按部就班的写常规代码了，能使出此等奇淫巧技？但肯定也勤奋，能开出这样的脑洞，上厕所的时候应该没少思考，从一个想法到能完成理想效果的代码，之间踩的坑，郁闷以及成功后的成就感，除了本人应该再无其他人能切实体会。
>
>非常感谢作者的贡献！大家鼓掌！不能再说下去了，不然自夸的行为将会暴露无遗！哈哈
>
>不要问我那个多选的写好了没，写好了我会不写博客？
>
>实话说，到现在还没完全搞懂MultiType作者的一些比较细的想法，我只领略了一二。没什么资格评价，但这脑洞一定得点赞呀！

[项目地址](https://github.com/GaoGersy/SimpleMutiTypeAdapter)
[听说你在找自动处理下拉刷新和上拉加载的框架？](http://www.jianshu.com/p/cc9b5dc2b203)




