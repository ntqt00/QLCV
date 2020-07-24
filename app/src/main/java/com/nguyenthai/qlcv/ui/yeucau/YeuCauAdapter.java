package com.nguyenthai.qlcv.ui.yeucau;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.nguyenthai.qlcv.R;
import com.nguyenthai.qlcv.Them_Sua_YeuCau_Activity;

public class YeuCauAdapter extends RecyclerView.Adapter<YeuCauAdapter.ViewHolder> {


    private Context mContext;
    private ArrayList<YeuCauModel> array_yeucau;

    public YeuCauAdapter(Context mContext, ArrayList<YeuCauModel> array_yeucau) {
        this.mContext = mContext;
        this.array_yeucau = array_yeucau;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.cardview_yeucau, parent, false);
        ViewHolder viewHolder = new ViewHolder(heroView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final YeuCauModel m_yeucau = array_yeucau.get(position);
        holder.txt_mayeucau.setText(m_yeucau.getMa_yeu_cau());
        holder.txt_tenduan.setText(m_yeucau.getTen_du_an());
        holder.txt_loaiyeucau.setText(m_yeucau.getLoai_yeu_cau());
        holder.txt_yeucau.setText(m_yeucau.getYeu_cau());
        holder.txt_donviyeucau.setText(m_yeucau.getDon_vi_yeu_cau());
        holder.txt_nguoitao.setText(m_yeucau.getNguoi_tao());
        holder.txt_nguoixuly.setText(m_yeucau.getNguoi_xu_ly());
        holder.txt_tanxuat.setText(m_yeucau.getTan_suat());
        holder.txt_mucdo.setText(m_yeucau.getMuc_do());
        holder.txt_uutien.setText(m_yeucau.getUu_tien());
        holder.txt_trangthai.setText(m_yeucau.getTrang_thai());
        holder.txt_guisms.setText(m_yeucau.getGui_sms());
        holder.txt_ngaytao.setText(m_yeucau.getNgay_tao());
        holder.txt_ngaybd.setText(m_yeucau.getNgay_bd());
        holder.txt_ngaykt.setText(m_yeucau.getNgay_kt());
        holder.txt_ngaykttt.setText(m_yeucau.getNgay_kttt());


        // Hiện menu nhấn giữ
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                final PopupMenu menu = new PopupMenu(mContext, view);
                menu.getMenuInflater().inflate(R.menu.chuc_nang, menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.m_chinhsua:
                                Toast.makeText(mContext, m_yeucau.getMa_yeu_cau().toString(), Toast.LENGTH_SHORT).show();

                                Them_Sua_YeuCau_Activity.trangthai = "chinh_sua";
                                Intent in = new Intent(mContext, Them_Sua_YeuCau_Activity.class);
                                mContext.startActivity(in);

                                break;
                            case R.id.m_xoa:
                                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
                                alertDialogBuilder.setMessage("Bạn có chắc chắn xóa?");
                                alertDialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        Toast.makeText(mContext, "Xóa thành công", Toast.LENGTH_LONG).show();
                                    }
                                });
                                alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                                break;
                        }
                        return true;
                    }
                });
                menu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return array_yeucau.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private TextView txt_mayeucau;
        private TextView txt_tenduan;
        private TextView txt_loaiyeucau;
        private TextView txt_yeucau;
        private TextView txt_donviyeucau;
        private TextView txt_nguoitao;
        private TextView txt_nguoixuly;
        private TextView txt_tanxuat;
        private TextView txt_mucdo;
        private TextView txt_uutien;
        private TextView txt_trangthai;
        private TextView txt_guisms;
        private TextView txt_ngaytao;
        private TextView txt_ngaybd;
        private TextView txt_ngaykt;
        private TextView txt_ngaykttt;


        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_mayeucau = itemView.findViewById(R.id.txt_yc_mayeucau);
            txt_tenduan = itemView.findViewById(R.id.txt_yc_tenyeucau);
            txt_loaiyeucau = itemView.findViewById(R.id.txt_yc_loaiyeucau);
            txt_yeucau = itemView.findViewById(R.id.txt_yc_ndyeucau);
            txt_donviyeucau = itemView.findViewById(R.id.txt_yc_donviyeucau);
            txt_nguoitao = itemView.findViewById(R.id.txt_yc_nguoitao);
            txt_nguoixuly = itemView.findViewById(R.id.txt_yc_nguoixuly);
            txt_tanxuat = itemView.findViewById(R.id.txt_yc_tansuat);
            txt_mucdo = itemView.findViewById(R.id.txt_yc_mucdo);
            txt_uutien = itemView.findViewById(R.id.txt_yc_uutien);
            txt_trangthai = itemView.findViewById(R.id.txt_yc_trangthai);
            txt_guisms = itemView.findViewById(R.id.txt_yc_guisms);
            txt_ngaytao = itemView.findViewById(R.id.txt_yc_ngaytao);
            txt_ngaybd = itemView.findViewById(R.id.txt_yc_ngaybatdau);
            txt_ngaykt = itemView.findViewById(R.id.txt_yc_ngayketthuc);
            txt_ngaykttt = itemView.findViewById(R.id.txt_yc_ngayketthuctt);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), true);
            return true;
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }


    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }

}