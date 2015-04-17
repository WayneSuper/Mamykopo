package cn.wayne.mamypoko.mode.noeat;

import java.util.List;

/**
 * Created by Pollux on 2015/4/15.
 * //
 */
public class NoeatDetailModel {
    /**
     * ret : 1
     * data : {"data":[{"content":"�������ڼ��٣�����������ѹ�ȵ�����ոս����θ���������ܶ��Ƚ��������Գ��ᷢ�����ء�����Ƭ���ڴ����࣬�ܴٽ��ű���ЧԤ�����ء����ǣ���ƬҲ�з�Ʒ�֣�������һ��Ҫѡ�ô�������Ƭ����ͨ����Ƭ���кܶ���Ӽ������˻ᵼ����ˮ���٣����������̡�","title":"�����ܳ���Ƭ��","can_eat":"2"},{"content":"��Ƭ���зḻ��B��ά���غ���ʳ��ά���ܹ�����ά���غ�Ԥ�����أ����и����������ʳ��������Ѹ�λ���裬���к��ھ�Ҫ�ٳ����󣬻������ˮ��","title":"�и��ܳ���Ƭ��","can_eat":"2"},{"content":"�����ڵ��������ƬҪ�ر�ע�⣬һ��Ҫѡ����������Ƭ������ʳ����ͨ��Ƭ��������Ƭ����ͨ��Ƭ����С�󡢴�����ѿ������ɰ�ǡ��̾��ȳɷ֣����������������ͨ��Ƭ���ܻᷢ���������󣬵�����ˮ���٣����������̣�Ӱ�챦����","title":"�������ܳ���Ƭ��","can_eat":"2"},{"content":"����Ӫ���ḻ�������Ѿ׽���Ӥ�׶����Դ�ɺ�״֮��ʳ�á��������˸�����ʳ�ù����Ŷ��Ҫ�����Ų���Ӱ�쵽���������������յ�Ŷ��","title":"Ӥ���ܳ���Ƭ��","can_eat":"1"}],"img":"http://filebaby.qubaobei.com/uploads/nbnc/600/2.jpg"}
     */
    private int ret;
    private DetailImage data;

    public void setRet(int ret) {
        this.ret = ret;
    }

    public void setData(DetailImage data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public DetailImage getData() {
        return data;
    }

    public class DetailImage {
        /**
         * data : [{"content":"�������ڼ��٣�����������ѹ�ȵ�����ոս����θ���������ܶ��Ƚ��������Գ��ᷢ�����ء�����Ƭ���ڴ����࣬�ܴٽ��ű���ЧԤ�����ء����ǣ���ƬҲ�з�Ʒ�֣�������һ��Ҫѡ�ô�������Ƭ����ͨ����Ƭ���кܶ���Ӽ������˻ᵼ����ˮ���٣����������̡�","title":"�����ܳ���Ƭ��","can_eat":"2"},{"content":"��Ƭ���зḻ��B��ά���غ���ʳ��ά���ܹ�����ά���غ�Ԥ�����أ����и����������ʳ��������Ѹ�λ���裬���к��ھ�Ҫ�ٳ����󣬻������ˮ��","title":"�и��ܳ���Ƭ��","can_eat":"2"},{"content":"�����ڵ��������ƬҪ�ر�ע�⣬һ��Ҫѡ����������Ƭ������ʳ����ͨ��Ƭ��������Ƭ����ͨ��Ƭ����С�󡢴�����ѿ������ɰ�ǡ��̾��ȳɷ֣����������������ͨ��Ƭ���ܻᷢ���������󣬵�����ˮ���٣����������̣�Ӱ�챦����","title":"�������ܳ���Ƭ��","can_eat":"2"},{"content":"����Ӫ���ḻ�������Ѿ׽���Ӥ�׶����Դ�ɺ�״֮��ʳ�á��������˸�����ʳ�ù����Ŷ��Ҫ�����Ų���Ӱ�쵽���������������յ�Ŷ��","title":"Ӥ���ܳ���Ƭ��","can_eat":"1"}]
         * img : http://filebaby.qubaobei.com/uploads/nbnc/600/2.jpg
         */
        private List<DataEntity> data;
        private String img;

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public List<DataEntity> getData() {
            return data;
        }

        public String getImg() {
            return img;
        }

        public class DataEntity {
            /**
             * content : �������ڼ��٣�����������ѹ�ȵ�����ոս����θ���������ܶ��Ƚ��������Գ��ᷢ�����ء�����Ƭ���ڴ����࣬�ܴٽ��ű���ЧԤ�����ء����ǣ���ƬҲ�з�Ʒ�֣�������һ��Ҫѡ�ô�������Ƭ����ͨ����Ƭ���кܶ���Ӽ������˻ᵼ����ˮ���٣����������̡�
             * title : �����ܳ���Ƭ��
             * can_eat : 2
             */
            private String content;
            private String title;
            private String can_eat;

            public void setContent(String content) {
                this.content = content;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setCan_eat(String can_eat) {
                this.can_eat = can_eat;
            }

            public String getContent() {
                return content;
            }

            public String getTitle() {
                return title;
            }

            public String getCan_eat() {
                return can_eat;
            }
        }
    }
}
