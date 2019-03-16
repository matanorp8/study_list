package com.example.matan.firtst_proyecton;
        import android.content.Context;
        import android.support.annotation.LayoutRes;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import java.util.ArrayList;
        import java.util.List;

public class SourcesArray extends ArrayAdapter<Source> {
    private Context mContext;
    private List<Source> SourcesList = new ArrayList<>();

    public SourcesArray(@NonNull Context context, @LayoutRes ArrayList<Source> list) {
        super(context, 0 , list);
        mContext = context;
        SourcesList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.source_item,parent,false);

        Source currentSource = SourcesList.get(position);

        TextView sourceName = (TextView) listItem.findViewById(R.id.sourceName);
        sourceName.setText(currentSource.getSourceName());

        TextView year = (TextView) listItem.findViewById(R.id.year);
        year.setText(String.valueOf(currentSource.getYear()));

        TextView writenBy = (TextView) listItem.findViewById(R.id.writenBy);
        writenBy.setText("Writen by: "+currentSource.getWritenBy());

        TextView language = (TextView) listItem.findViewById(R.id.language);
        language.setText("Language: " +currentSource.getLanguage());

        return listItem;

    }
}


